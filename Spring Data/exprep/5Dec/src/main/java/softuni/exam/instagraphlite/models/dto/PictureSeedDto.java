package softuni.exam.instagraphlite.models.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.*;

public class PictureSeedDto {
    @Expose
    private String path;
    @Expose
    private Double size;


    @Min(500)
    @Max(60000)
    @NotNull
    public Double getSize() {
        return size;
    }

    @NotBlank
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

    public void setSize(Double size) {
        this.size = size;
    }
}
