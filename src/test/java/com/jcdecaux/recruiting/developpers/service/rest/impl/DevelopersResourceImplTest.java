package com.jcdecaux.recruiting.developpers.service.rest.impl;

import com.jcdecaux.recruiting.developpers.domain.service.IdevelopersService;
import com.jcdecaux.recruiting.developpers.domain.service.messages.ErrorMessages;
import com.jcdecaux.recruiting.developpers.service.rest.FunctionalException;
import com.jcdecaux.recruiting.developpers.service.rest.dto.DeveloperDTO;
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
import java.util.Optional;

public class DevelopersResourceImplTest {

    @InjectMocks
    private DevelopersResourceImpl idevelopersResource;
    @Mock
    private IdevelopersService idevelopersService;
    @Mock
    UriInfo uriInfo;

    private static final String HOST_PORT = "http://localhost:8080";
    private static final String CREATE_OPERATION_URL =HOST_PORT+"/developers-api/developers/1";

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_viewDeveloper_when_id_exist(){

        Optional<DeveloperDTO> developerDTO = Optional.of(initDeveloperDto());
        Mockito.when(idevelopersService.viewDeveloper(1)).thenReturn(developerDTO);
        Response response = idevelopersResource.viewDeveloper(1);
        DeveloperDTO responseEntity = (DeveloperDTO) response.getEntity();
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(DeveloperDTO.class,responseEntity.getClass());
        Assert.assertNotNull(responseEntity.getFirstName());
        Assert.assertNotNull(responseEntity.getLastName());
        Assert.assertNotNull(responseEntity.getId());
    }

    @Test
    public void test_viewDeveloper_when_id_not_exist(){

        Mockito.when(idevelopersService.viewDeveloper(2)).thenReturn(Optional.empty());
        Response response = idevelopersResource.viewDeveloper(2);
        Assert.assertEquals(Response.Status.NO_CONTENT.getStatusCode(),response.getStatus());
    }

    @Test(expected = FunctionalException.class)
    public void test_getDevelopers_when_language_is_null(){
        try {
            idevelopersResource.getDevelopers(null);
        }catch (FunctionalException functionalException){
            Assert.assertEquals(Response.Status.BAD_REQUEST,functionalException.getStatus());
            Assert.assertEquals(ErrorMessages.CHECK_PARAMETERS_EMPTY_ERROR,functionalException.getDescription());
            throw  functionalException;
        }
    }

    @Test
    public void test_getDevelopers_when_language_not_exist(){
        Mockito.when(idevelopersService.getDevelopers("wrongLanguage")).thenReturn(new ArrayList<>());
        Response responseEntity = idevelopersResource.getDevelopers("wrongLanguage");
        Assert.assertEquals(Response.Status.NO_CONTENT.getStatusCode(),responseEntity.getStatus());
    }

    @Test
    public void test_getDevelopers_when_language_exist(){
        List<DeveloperDTO> listDevelopersDto = new ArrayList<>();
        listDevelopersDto.add(initDeveloperDto());
        Mockito.when(idevelopersService.getDevelopers("JAVA")).thenReturn(listDevelopersDto);
        Response responseEntity = idevelopersResource.getDevelopers("JAVA");
        List<DeveloperDTO> listDevelopers = (List<DeveloperDTO> ) responseEntity.getEntity();
        Assert.assertEquals(true,!listDevelopers.isEmpty());

    }

    @Test(expected = FunctionalException.class)
    public void test_create_developers_when_empty_parameters(){
        try {
            idevelopersResource.createDevelopers(new ArrayList<>());
        }catch (FunctionalException functionalException){
                Assert.assertEquals(Response.Status.BAD_REQUEST,functionalException.getStatus());
                Assert.assertEquals(ErrorMessages.CHECK_PARAMETERS_EMPTY_ERROR,functionalException.getDescription());
                throw functionalException;
        }
    }

    @Test
    public void test_create_developers_when_response_success(){
        List<Integer> idsdevelopers = new ArrayList<>();
        List<DeveloperDTO> developers = new ArrayList<>();
        idsdevelopers.add(initDeveloperDto().getId());
        developers.add(initDeveloperDto());
        Mockito.when(idevelopersService.createDevelopers(developers)).thenReturn(idsdevelopers);
        try {
            Mockito.when(uriInfo.getRequestUri()).thenReturn(new URI(HOST_PORT+"/developers-api/developers/"));
        }catch (URISyntaxException uriSyntaxException){
            Assert.fail();
        }
        Response responseEntity = idevelopersResource.createDevelopers(developers);
        List<URI> uris = (List<URI>) responseEntity.getEntity();
        Assert.assertEquals(Response.Status.CREATED.getStatusCode(),responseEntity.getStatus());
        Assert.assertNotNull(uris);
        Assert.assertEquals(CREATE_OPERATION_URL,uris.get(0).toString());
    }


    private DeveloperDTO initDeveloperDto(){
        DeveloperDTO developerDTO = new DeveloperDTO();
        developerDTO.setFirstName("Amine");
        developerDTO.setLastName("Benhalima");
        developerDTO.setId(1);
        return developerDTO;
    }
}
