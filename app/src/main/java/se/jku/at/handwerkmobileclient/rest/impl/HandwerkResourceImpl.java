package se.jku.at.handwerkmobileclient.rest.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import se.jku.at.handwerkmobileclient.model.DateParam;
import se.jku.at.handwerkmobileclient.model.Manufacturer;
import se.jku.at.handwerkmobileclient.model.ManufacturerList;
import se.jku.at.handwerkmobileclient.model.Service;
import se.jku.at.handwerkmobileclient.model.ServiceCategory;
import se.jku.at.handwerkmobileclient.model.ServiceCategoryList;
import se.jku.at.handwerkmobileclient.model.ServiceList;
import se.jku.at.handwerkmobileclient.rest.HandwerkResource;

/**
 * Created by Martin on 12.05.15.
 */
public class HandwerkResourceImpl implements HandwerkResource {

    @Override
    public ManufacturerList getAllManufacturers() {
        try {
            ManufacturerList list;
            WebTarget service = RestHelper.getWebTarget();
            service = service.path("manufacturers");
            Invocation.Builder builder = service.request().accept(MediaType.APPLICATION_JSON);
            Response response = builder.get();
            String json = response.readEntity(String.class);
            ObjectMapper mapper = new ObjectMapper();
            list = mapper.readValue(json, ManufacturerList.class);
            return list;
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

    @Override
    public Manufacturer getManufacturer(int id) {
        try {
            Manufacturer worker;
            WebTarget service = RestHelper.getWebTarget();
            service = service.path("manufacturers").path(id + "");
            Invocation.Builder builder = service.request().accept(MediaType.APPLICATION_JSON);
            Response response = builder.get();
            String json = response.readEntity(String.class);
            ObjectMapper mapper = new ObjectMapper();
            worker = mapper.readValue(json, Manufacturer.class);
            return worker;
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

    @Override
    public ManufacturerList getManufacturers(String name, String address, String city, String country, int plz, String email) {
        try {
            ManufacturerList list;
            WebTarget service = RestHelper.getWebTarget();
            service = service.path("manufacturers");
            service = service.queryParam("name", name);
            service = service.queryParam("address", address);
            service = service.queryParam("city", city);
            service = service.queryParam("country", country);
            service = service.queryParam("plz", plz);
            service = service.queryParam("email", email);

            Invocation.Builder builder = service.request().accept(MediaType.APPLICATION_JSON);
            Response response = builder.get();
            String json = response.readEntity(String.class);
            ObjectMapper mapper = new ObjectMapper();
            list = mapper.readValue(json, ManufacturerList.class);
            return list;
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteManufacturer(int id) {
        try {
            WebTarget service = RestHelper.getWebTarget();
            service = service.path("manufacturers");
            service = service.path(id + "");
            Invocation.Builder builder = service.request().accept(MediaType.TEXT_PLAIN);
            Response response = builder.delete();
            String json = response.readEntity(String.class);
            return json.equals("true");
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addManufacturer(Manufacturer worker) {
        try {
            WebTarget service = RestHelper.getWebTarget();
            service = service.path("manufacturers");
            ObjectMapper mapper = new ObjectMapper();
            String inputjson = mapper.writeValueAsString(worker);
            Entity<String> entity = Entity.entity(inputjson, MediaType.APPLICATION_JSON);
            Invocation.Builder builder = service.request().accept(MediaType.TEXT_PLAIN);
            Response response = builder.put(entity);
            String json = response.readEntity(String.class);
            return json.equals("true");
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
    }

    @Override
    public ServiceList getAllServices() {
        try {
            ServiceList list;
            WebTarget service = RestHelper.getWebTarget();
            service = service.path("services");
            Invocation.Builder builder = service.request().accept(MediaType.APPLICATION_JSON);
            Response response = builder.get();
            String json = response.readEntity(String.class);
            ObjectMapper mapper = new ObjectMapper();
            list = mapper.readValue(json, ServiceList.class);
            return list;
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

    @Override
    public Service getService(int id) {
        try {
            Service work;
            WebTarget service = RestHelper.getWebTarget();
            service = service.path("services").path(id + "");
            Invocation.Builder builder = service.request().accept(MediaType.APPLICATION_JSON);
            Response response = builder.get();
            String json = response.readEntity(String.class);
            ObjectMapper mapper = new ObjectMapper();
            work = mapper.readValue(json, Service.class);
            return work;
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

    @Override
    public ServiceList getServices(ServiceCategory categorie, double price, DateParam creationDate, int manId, String country, String city, int plz) {
        try {
            ServiceList list;
            WebTarget service = RestHelper.getWebTarget();
            service = service.path("services");
            service = service.queryParam("cat", categorie);
            service = service.queryParam("price", price);
            service = service.queryParam("date", creationDate);
            service = service.queryParam("manufacturer", manId);
            service = service.queryParam("country", country);
            service = service.queryParam("city", city);
            service = service.queryParam("plz", plz);
            Invocation.Builder builder = service.request().accept(MediaType.APPLICATION_JSON);
            Response response = builder.get();
            String json = response.readEntity(String.class);
            ObjectMapper mapper = new ObjectMapper();
            list = mapper.readValue(json, ServiceList.class);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteService(int id) {
        try {
            WebTarget service = RestHelper.getWebTarget();
            service = service.path("services");
            service = service.path(id + "");
            Invocation.Builder builder = service.request().header("Content-Type","text/plain");
            Response response = builder.delete();
            String json = response.readEntity(String.class);
            return json.equals("true");
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addService(Service service) {
        try {
            WebTarget target = RestHelper.getWebTarget();
            target = target.path("services");
            ObjectMapper mapper = new ObjectMapper();
            String inputjson = mapper.writeValueAsString(service);
            Entity<String> entity = Entity.entity(inputjson, MediaType.APPLICATION_JSON);
            Invocation.Builder builder = target.request().accept(MediaType.TEXT_PLAIN);
            Response response = builder.put(entity);
            String json = response.readEntity(String.class);
            return json.equals("true");
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
    }

    @Override
    public ServiceCategoryList getServiceCategories() {
        try {
            ServiceCategoryList list;
            WebTarget service = RestHelper.getWebTarget();
            service = service.path("services/categories");
            Invocation.Builder builder = service.request().accept(MediaType.APPLICATION_JSON);
            Response response = builder.get();
            String json = response.readEntity(String.class);
            ObjectMapper mapper = new ObjectMapper();
            list = mapper.readValue(json, ServiceCategoryList.class);
            return list;
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }
}
