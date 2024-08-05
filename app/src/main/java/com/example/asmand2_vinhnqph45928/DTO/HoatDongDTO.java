package com.example.asmand2_vinhnqph45928.DTO;

public class HoatDongDTO {
    private String mahd;
    private String ngayghi;
    private String ghichu;

    public HoatDongDTO(String mahd, String ngayghi, String ghichu) {
        this.mahd = mahd;
        this.ngayghi = ngayghi;
        this.ghichu = ghichu;
    }

    public HoatDongDTO() {
    }

    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public String getNgayghi() {
        return ngayghi;
    }

    public void setNgayghi(String ngayghi) {
        this.ngayghi = ngayghi;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
}
