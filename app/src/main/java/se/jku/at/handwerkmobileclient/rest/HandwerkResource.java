package se.jku.at.handwerkmobileclient.rest;

import se.jku.at.handwerkmobileclient.model.*;

/**
 * Stellt schnittstellen zur Verwendung der Webservices aus dem HandwerkService zur verf�gung
 */
public interface HandwerkResource {


    /**
     * Retourniert alle Manufacturer aus dem HandwerkService
     * @return
     */
    public ManufacturerList getAllManufacturers();

    /**
     * Retourniert den Manufacturer mit einer bestimmten id aus dem HandwerkService
     * @param id
     * @return
     */
    public Manufacturer getManufacturer(int id);

    /**
     * Retourniert den Manufacturer aus dem HandwerkService unter ber�cksichtung von Filterkriterien
     * @param name nur Manufacturer mit bestimmten Namen
     * @param address nur Manufacturer die an einer bestimmten Adresse wohnen
     * @param city nur Manufacturer die in einem bestimmten Ort wohnen
     * @param country nur Manufacturer die in einem bestimmten Land wohnen
     * @param plz nur Manufacturer die in einem Ort mit bestimmter PLZ wohnen
     * @param email nur Manufacturer die eine bestimmte email-adresse haben
     * @return
     */
    public ManufacturerList getManufacturers(String name, String address,
                                             String city, String country, int plz, String email);

    /**
     * L�scht den Manufacturer mit der bestimmten id aus dem HandwerkService
     * @param id
     * @return
     */
    public boolean deleteManufacturer(int id);

    /**
     * F�gt den gegebenen Manufacturer dem HandwerkService hinzu
     * @param worker
     * @return
     */
    public boolean addManufacturer(Manufacturer worker);

    /**
     * Retourniert alle Services aus dem Handwerkservice
     * @return
     */
    public ServiceList getAllServices();

    /**
     * Retourniert das Service mit der bestimmten id aus dem HandwerkService
     * @param id
     * @return
     */
    public Service getService(int id);

    /**
     * Retourniert eine Liste von Services aus dem HandwerkService unter ber�cksichtigung bestimmter Filterkriterien
     * @param categorie nur Services dieser Kategorie
     * @param price nur Services deren Preis geringer ist als der gegebene
     * @param creationDate nur Services die nach dem gegebenen Datum erstellt wurden
     * @param manId nur Services eines bestimmten Manufacturer
     * @param country nur Services in denen der Manufacturer in einem bestimmten Land wohnt
     * @param city nur Services in denen der Manufacturer in einem bestimmten Ort wohnt
     * @param plznur Services in denen der Manufacturer in einem Ort mit der bestimmten PLZ wohnt
     * @return
     */
    public ServiceList getServices(ServiceCategory categorie,double price,DateParam creationDate,
                                   int manId,String country,String city,int plz);

    /**
     * L�scht Service mit bestimmter id aus dem HandwerkService
     * @param id
     * @return
     */
    public boolean deleteService(int id);

    /**
     * F�gt dem HandwerkService das gegebene Service hinzu
     * @param service
     * @return
     */
    public boolean addService(Service service);

    /**
     * Retourniert alle Kategorieen des HandwerkServices
     * @return
     */
    public ServiceCategoryList getServiceCategories();

}
