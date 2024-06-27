package org.example.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.HttpError;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.example.entity.Person;
import org.hibernate.Session;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Start page of application ApacheTapestryApp.
 */
public class Index {

    private static final Logger logger = LogManager.getLogger(Index.class);

    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    @Property
    @Inject
    @Symbol(SymbolConstants.TAPESTRY_VERSION)
    private String tapestryVersion;

    @InjectPage
    private About about;

    @InjectPage
    private PersonListPage personListPage;

    @Inject
    PageRenderLinkSource pageRenderLinkSource;

    @Property
    @Persist
    private String message;


    @Inject
    private Block block;




    public Object onActionFromGoToPersonList(){
        return pageRenderLinkSource.createPageRenderLink(PersonListPage.class);
    }


    // Handle call with an unwanted context
    Object onActivate(EventContext eventContext)
    {
        return eventContext.getCount() > 0 ?
            new HttpError(404, "Resource not found") :
            null;
    }

    Object onActionFromLearnMore()
    {
        about.setLearn("LearnMore");

        return about;
    }

    @Log
    void onComplete()
    {
      logger.info("Complete call on Index page");
    }

    @Log
    void onAjax()
    {
        logger.info("Ajax call on Index page");

        ajaxResponseRenderer.addRender("middlezone", block);
    }

    public ZonedDateTime getCurrentTime()
    {
        return ZonedDateTime.now();
    }

}
