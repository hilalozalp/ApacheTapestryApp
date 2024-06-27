package org.example.pages;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.http.services.Request;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.example.entity.PersonEditBean;
import org.example.services.PersonUCC;

public class PersonEditPage {

    @Property
    private PersonEditBean personEditBean;

    @Inject
    private PersonUCC personUCC;

    @Inject
    private Request request;

    private Long personId;

    void onActivate(Long personId) {
        this.personId = personId;
    }

    void onPrepare() {
        if (personId != null) {
            personEditBean = personUCC.findPersonById(personId);
            System.out.println("Loaded personEditBean for personId: " + personId);
        } else {
            personEditBean = new PersonEditBean();
            System.out.println("Initialized new personEditBean");
        }
    }


    Long onPassivate() {
        return personId;
    }

    Object onSuccess() {
        personUCC.saveOrUpdatePerson(personEditBean);
        return PersonListPage.class;
    }


}
