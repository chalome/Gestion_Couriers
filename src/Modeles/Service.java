package Modeles;

public class Service {

    private int serviceID;
    private String serviceNom;

    public Service() {
    }

    public Service(int serviceID, String serviceNom) {
        this.serviceID = serviceID;
        this.serviceNom = serviceNom;
    }

    public Service(String serviceNom) {
        this.serviceNom = serviceNom;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceNom() {
        return serviceNom;
    }

    public void setServiceNom(String serviceNom) {
        this.serviceNom = serviceNom;
    }

    @Override
    public String toString() {
        return serviceNom;
    }

}
