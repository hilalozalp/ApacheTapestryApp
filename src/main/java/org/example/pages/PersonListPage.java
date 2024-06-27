package org.example.pages;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.example.entity.PersonEditBean;
import org.example.entity.PersonListBean;
import org.example.services.PersonUCC;

import java.time.LocalDate;
import java.util.List;

public class PersonListPage {
    @Property
    private PersonListBean personListBean;

    @Property
    private PersonEditBean personEditBean;

    @Property
    private List<PersonListBean> personList;

    @Inject
    private PersonUCC personUCC;

    @InjectPage
    private PersonEditPage personEditPage;

    @Inject
    PageRenderLinkSource pageRenderLinkSource;

    void setupRender() {
        personUCC.createTestData();
        personList = personUCC.findAllPersons();
        System.out.println("SetupRender: Person list size = " + personList.size());
    }

    Object onActionFromCreate() {
        System.out.println("onActionFromCreate: Navigating to PersonEditPage to create a new person.");
        return pageRenderLinkSource.createPageRenderLink(PersonEditPage.class);
    }

    /*Object onActionFromEdit(Long personId) {
        personEditPage.onActivate(personId);
        return personEditPage;
    }*/

}