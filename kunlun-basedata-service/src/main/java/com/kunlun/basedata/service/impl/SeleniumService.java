package com.kunlun.basedata.service.impl;

import com.kunlun.basedata.model.IconModel;
import com.kunlun.basedata.model.selenium.PageAction;
import com.kunlun.basedata.model.selenium.PageModel;
import com.kunlun.basedata.service.IIconService;
import com.kunlun.basedata.service.ISeleniumService;
import com.kunlun.basedata.utils.CommonUtil;
import org.apache.commons.collections4.ListUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class SeleniumService implements ISeleniumService {

    private Logger log = LogManager.getLogger();

    private static final String CLASS_PATH = "src/main/resources";

    private static final String SELENIUM_PATH = "selenium";

    private static final String FILE_NAME = "pageConfig.xml";

    private static final String CHROME_NAME = "Chrome";

    private static final String DRIVER_NAME_PREFIX = "chromedriver-";

    private static final String DRIVER_NAME_SUFFIX = ".exe";

    @Value("${spring.application.name}")
    private String serviceName;

    @Autowired
    private IIconService iconService;

    public void executeStart(HttpServletRequest request) throws Exception {
        log.info("===== SeleniumService executeStart =====");

        // 加载chrome浏览器驱动
        loadChromeDriver(request);

        // 用Dom4j解析xml文件
        List<PageModel> pages = prasePageXml();

        // 执行selenium
        Set<String> iconSet = executeAction(pages);

        // 处理抓取到的icon
        List<IconModel> iconModels = new ArrayList<>();
        for (String icon : iconSet) {
            String iconName = icon;
            if (icon.startsWith("ri-") && (icon.endsWith("-fill") || icon.endsWith("-line"))) {
                iconName = icon.substring(3, icon.length() - 5);
            } else if (icon.startsWith("ri-") && !icon.endsWith("-fill") && !icon.endsWith("-line")) {
                iconName = icon.substring(3, icon.length());
            }

            IconModel model = new IconModel();
            model.setId(CommonUtil.generateUUID());
            model.setKey(icon);
            model.setName(iconName);
            model.setCreateTime(new Date());
            model.setModifiedTime(new Date());
            iconModels.add(model);
        }
        List<List<IconModel>> results = ListUtils.partition(iconModels, 500);
        iconService.deleteAllIcon();
        for (List<IconModel> list : results) {
            iconService.insertBatch(list);
        }
    }

    private void loadChromeDriver(HttpServletRequest request) {
        // 获取项目路径
        String filePath = getFilePath(DRIVER_NAME_PREFIX);

        // 获取chrome浏览器版本
        String userAgent = request.getHeader("User-Agent");
        String browserInfo = (userAgent.substring(userAgent.indexOf(CHROME_NAME)).split(" ")[0]).replace("/", "-");
        String chromeVersion = browserInfo.split("-")[1];
        String version = chromeVersion.substring(0, chromeVersion.indexOf("."));
        filePath += version + DRIVER_NAME_SUFFIX;

        // 设置chrome浏览器驱动到系统变量
        System.setProperty("webdriver.chrome.driver", filePath);
        log.info("ChromeDriver filePath ===>>>> " + filePath);
    }

    private String getFilePath(String fileName) {
        String classPath = System.getProperty("user.dir");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(classPath);
        stringBuilder.append("\\");
        stringBuilder.append(serviceName);
        stringBuilder.append("\\src\\main\\resources\\");
        stringBuilder.append(SELENIUM_PATH);
        stringBuilder.append("\\");
        stringBuilder.append(fileName);
        return stringBuilder.toString();
    }

    private List<PageModel> prasePageXml() {
        String filePath = getFilePath(FILE_NAME);
        List<PageModel> pages = new ArrayList<>();
        log.info("pageConfig filePath ===>>> " + filePath);

        try {
            File file = new File(filePath);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(file);
            Element element = document.getRootElement();

            List nodes = element.elements("page");
            for (Object pageObj : nodes) {
                Element pageElement = (Element) pageObj;
                Attribute name = pageElement.attribute("name");
                Attribute url = pageElement.attribute("url");
                List<PageAction> pageActions = new ArrayList<>();
                List actions = ((Element) pageObj).elements("action");
                for (Object actionObj : actions) {
                    PageAction pageAction = new PageAction();
                    Element actionElement = (Element) actionObj;
                    Attribute type = actionElement.attribute("type");
                    if ("navigate".equals(type.getValue())) {
                        pageAction.setType(type.getValue());
                        pageActions.add(pageAction);
                    } else if ("sendKeys".equals(type.getValue())) {
                        Attribute byid = actionElement.attribute("byxpath");
                        Attribute text = actionElement.attribute("text");
                        pageAction.setType(type.getValue());
                        pageAction.setByxpath(byid.getValue());
                        pageAction.setText(text.getValue());
                        pageActions.add(pageAction);
                    } else if ("click".equals(type.getValue())) {
                        Attribute byxpath = actionElement.attribute("byxpath");
                        Attribute waitTime = actionElement.attribute("waitTime");
                        pageAction.setType(type.getValue());
                        pageAction.setByxpath(byxpath.getValue());
                        pageAction.setWaitTime(waitTime.getValue());
                        pageActions.add(pageAction);
                    } else if ("get".equals(type.getValue())) {
                        Attribute byxpath = actionElement.attribute("byxpath");
                        Attribute waitTime = actionElement.attribute("waitTime");
                        pageAction.setType(type.getValue());
                        pageAction.setByxpath(byxpath.getValue());
                        pageAction.setWaitTime(waitTime.getValue());
                        pageActions.add(pageAction);
                    } else if ("executeScript".equals(type.getValue())) {
                        Attribute script = actionElement.attribute("script");
                        pageAction.setType(type.getValue());
                        pageAction.setScript(script.getValue());
                        pageActions.add(pageAction);
                    } else {
                        Attribute byid = actionElement.attribute("byid");
                        Attribute waitTime = actionElement.attribute("waitTime");
                        pageAction.setType(type.getValue());
                        pageAction.setByid(byid.getValue());
                        pageAction.setWaitTime(waitTime.getValue());
                        pageActions.add(pageAction);
                    }
                }

                PageModel page = new PageModel();
                page.setName(name.getValue());
                page.setUrl(url.getValue());
                page.setActions(pageActions);
                pages.add(page);
            }
        } catch (Exception e) {
            log.error("SeleniumService prasePageXml Error: ", e);
        }
        return pages;
    }

    private Set<String> executeAction(List<PageModel> pages) {
        Set<String> iconSets = new HashSet<>();
        try {
            // 设置为 headless 模式 （无头浏览器）或使用phantomjs.exe，但后者对使用ES6的页面支持不好
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);

            String url = pages.get(0).getUrl();
            List<PageAction> actions = pages.get(0).getActions();
            for (PageAction action : actions) {
                if ("navigate".equals(action.getType())) {
                    chromeDriver.get(url);
                } else if ("sendKeys".equals(action.getType())) {
                    chromeDriver.findElement(By.id(action.getByid())).sendKeys(action.getText());
                } else if ("click".equals(action.getType())) {
                    long waitTime = Long.parseLong(action.getWaitTime());
                    chromeDriver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
                    chromeDriver.findElement(By.xpath(action.getByxpath())).click();
                } else if ("get".equals(action.getType())) {
                    long waitTime = Long.parseLong(action.getWaitTime());
                    chromeDriver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
                    List<WebElement> iElementList = chromeDriver.findElements(By.tagName("i"));
                    for (WebElement element : iElementList) {
                        String className = element.getAttribute("class");
                        if (!ObjectUtils.isEmpty(className) && !"add-btn".equals(className)) {
                            iconSets.add(className);
                        }
                    }
                    System.out.println();
                } else if ("executeScript".equals(action.getType())) {
                    chromeDriver.executeScript(action.getScript());
                } else {
                    long waitTime = Long.parseLong(action.getWaitTime());
                    chromeDriver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
                    chromeDriver.findElement(By.id(action.getByid())).click();
                }
            }
            chromeDriver.close();
            chromeDriver.quit();
        } catch (Exception e) {
            log.error("SeleniumService executeAction Error: ", e);
        }
        return iconSets;
    }
}
