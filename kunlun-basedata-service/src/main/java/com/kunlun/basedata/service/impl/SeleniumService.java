package com.kunlun.basedata.service.impl;

import com.kunlun.basedata.model.selenium.PageAction;
import com.kunlun.basedata.model.selenium.PageModel;
import com.kunlun.basedata.service.ISeleniumService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class SeleniumService implements ISeleniumService {

    private Logger log = LogManager.getLogger();

    private static final String CLASS_PATH = "src/main/resources";

    private static final String SELENIUM_PATH = "selenium";

    private static final String FILE_NAME = "pageConfig.xml";

    private static final String DRIVER_NAME = "chromedriver.exe";

    @Value("${spring.application.name}")
    private String serviceName;

    public void executeStart() throws Exception {
        // 加载chrome驱动
        loadChromeDriver();

        // 用Dom4j解析xml文件
        List<PageModel> pages = praseXml();

        // 执行selenium
        executeAction(pages);
    }

    private String getFilePath(String fileName) {
        String classPath = System.getProperty("user.dir");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(classPath.substring(0, classPath.lastIndexOf("\\")));
        stringBuilder.append("\\");
        stringBuilder.append(serviceName);
        stringBuilder.append("\\src\\main\\resources\\");
        stringBuilder.append(SELENIUM_PATH);
        stringBuilder.append("\\");
        stringBuilder.append(fileName);
        return stringBuilder.toString();
    }

    private void loadChromeDriver() {
        String filePath = getFilePath(DRIVER_NAME);
        System.setProperty("webdriver.chrome.driver", filePath);
    }

    private List<PageModel> praseXml() {
        String filePath = getFilePath(FILE_NAME);
        List<PageModel> pages = new ArrayList<PageModel>();

        try {
            File file = new File(filePath);

            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(file);
            Element element = document.getRootElement();

            List nodes = element.elements("page");
            for (Object pageObj : nodes) {
                Element pageElement = (Element)pageObj;
                Attribute name = pageElement.attribute("name");
                Attribute url = pageElement.attribute("url");

                List<PageAction> pageActions = new ArrayList<PageAction>();
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
            e.printStackTrace();
        }
        return pages;
    }

    private void executeAction(List<PageModel> pages) {
        String url = null;
        List<PageAction> actions = null;
        ChromeDriver chromeDriver = null;
        try {
            url = pages.get(0).getUrl();
            actions = pages.get(0).getActions();
            chromeDriver = new ChromeDriver();
            chromeDriver.manage().window().maximize();
            Thread.sleep(2000);
            //chromeDriver.manage().window().setSize(new Dimension(500, 500));

            for (PageAction action : actions) {
                if ("navigate".equals(action.getType())) {
                    chromeDriver.get(url);
                } else if ("sendKeys".equals(action.getType())) {
                    chromeDriver.findElement(By.id(action.getByid())).sendKeys(action.getText());
                } else if ("executeScript".equals(action.getType())) {
                    chromeDriver.executeScript(action.getScript());
                } else {
                    long waitTime = Long.parseLong(action.getWaitTime());
                    chromeDriver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
                    Thread.sleep(waitTime);
                    chromeDriver.findElement(By.id(action.getByid())).click();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
