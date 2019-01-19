package com.jcdecaux.recruiting.developpers.service.rest.impl;

import com.jcdecaux.recruiting.developpers.domain.service.IlanguagesService;
import com.jcdecaux.recruiting.developpers.domain.service.messages.ErrorMessages;
import com.jcdecaux.recruiting.developpers.service.rest.FunctionalException;
import com.jcdecaux.recruiting.developpers.service.rest.dto.LanguageDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class LanguagesResourceImplTest {

    @InjectMocks
    LanguagesResourceImpl languagesResource;
    @Mock
    IlanguagesService ilanguagesService;
    @Mock
    UriInfo uriInfo;
    private final static String HOST_PORT = "http://localhost:8080";
    private final static String CREATE_LANGUAGES_OPERATION = HOST_PORT+"/developers-api/languages/1";

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_viewLanguage_when_return_success(){
        Mockito.when(ilanguagesService.viewLanguage(1)).thenReturn(initLanguageDto());
        Response responseEntity = languagesResource.viewLanguage(1);
        LanguageDTO languageDTO = (LanguageDTO) responseEntity.getEntity();
        Assert.assertEquals(Response.Status.OK.getStatusCode(),responseEntity.getStatus());
        Assert.assertEquals(LanguageDTO.class,languageDTO.getClass());
    }

    @Test
    public void test_createLanguages_when_empty_parameters(){
        try {
            languagesResource.createLanguages(new ArrayList<>());
        }catch (FunctionalException functionalException){
            Assert.assertEquals(Response.Status.BAD_REQUEST,functionalException.getStatus());
            Assert.assertEquals(ErrorMessages.CHECK_PARAMETERS_EMPTY_ERROR,functionalException.getDescription());
            throw functionalException;
        }
    }

    @Test
    public void test_createLanguages_when_return_success(){
        List<Integer> idsLanguages = new ArrayList<>();
        List<LanguageDTO> listLanguages = new ArrayList<>();
        listLanguages.add(initLanguageDto());
        idsLanguages.add(1);
        Mockito.when(ilanguagesService.createLanguages(listLanguages)).thenReturn(idsLanguages);
        try {
            Mockito.when(uriInfo.getRequestUri()).thenReturn(new URI(HOST_PORT+"/developers-api/languages/"));
        }catch (URISyntaxException uriSyntaxException){
            Assert.fail();
        }
        Response responseEntity = languagesResource.createLanguages(listLanguages);
        List<URI> uris = (List<URI>) responseEntity.getEntity();
        Assert.assertEquals(Response.Status.CREATED.getStatusCode(),responseEntity.getStatus());
        Assert.assertEquals(CREATE_LANGUAGES_OPERATION,uris.get(0).toString());

    }

    private LanguageDTO initLanguageDto(){
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setName("JAVA");
        languageDTO.setVersion("7");
        return  languageDTO;
    }
}
