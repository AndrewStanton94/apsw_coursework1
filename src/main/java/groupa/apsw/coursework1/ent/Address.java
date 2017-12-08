package groupa.apsw.coursework1.ent;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alpha
 */
@Entity
public class Address implements Serializable {
    @Id
    private long id;
    private String company;
    @NotNull
    private String firstLine;
    private String secondLine;
    @NotNull
    private String city;
    private String county;
    @NotNull
    private String postcode;
    @NotNull
    private String country;
    @NotNull
    @OneToOne(mappedBy="address")
    private SystemUser resident;

    public SystemUser getResident() {
        return resident;
    }

    public void setResident(SystemUser resident) {
        this.resident = resident;
    }

    public Address() {
    }

    public Address(String firstLine, String city, String postcode, String country) {
        this.firstLine = firstLine;
        this.city = city;
        this.postcode = postcode;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFirstLine() {
        return firstLine;
    }

    public void setFirstLine(String firstLine) {
        this.firstLine = firstLine;
    }

    public String getSecondLine() {
        return secondLine;
    }

    public void setSecondLine(String secondLine) {
        this.secondLine = secondLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    
}
