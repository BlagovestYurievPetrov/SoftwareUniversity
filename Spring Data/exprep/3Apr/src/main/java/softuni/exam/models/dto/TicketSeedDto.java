package softuni.exam.models.dto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class TicketSeedDto {
    @XmlElement(name = "serial-number")
    private String SerialNumber;
    @XmlElement(name = "price")
    private BigDecimal price;
    @XmlElement(name = "take-off")
    private String takeoff;
    @XmlElement(name = "from-town")
    private FromTownDto fromTownDto;
    @XmlElement(name = "to-town")
    private ToTownDto toTownDto;
    @XmlElement(name = "passenger")
    private PassengerEmailDto passengerEmailDto;
    @XmlElement(name = "plane")
    private PlaneRegisterNumberDto planeRegisterNumberDto;

    @Size(min = 2)
    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }
    @Positive
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTakeoff() {
        return takeoff;
    }

    public void setTakeoff(String takeoff) {
        this.takeoff = takeoff;
    }

    public FromTownDto getFromTownDto() {
        return fromTownDto;
    }

    public void setFromTownDto(FromTownDto fromTownDto) {
        this.fromTownDto = fromTownDto;
    }

    public ToTownDto getToTownDto() {
        return toTownDto;
    }

    public void setToTownDto(ToTownDto toTownDto) {
        this.toTownDto = toTownDto;
    }

    public PassengerEmailDto getPassengerEmailDto() {
        return passengerEmailDto;
    }

    public void setPassengerEmailDto(PassengerEmailDto passengerEmailDto) {
        this.passengerEmailDto = passengerEmailDto;
    }

    public PlaneRegisterNumberDto getPlaneRegisterNumberDto() {
        return planeRegisterNumberDto;
    }

    public void setPlaneRegisterNumberDto(PlaneRegisterNumberDto planeRegisterNumberDto) {
        this.planeRegisterNumberDto = planeRegisterNumberDto;
    }
}
