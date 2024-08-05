package com.example.asmand2_vinhnqph45928.DTO;

public class VanDongDTO {
    private String maVD;
    private String ngayVD;
    private String muctieu;
    private String hoanthanh;

    public VanDongDTO(String maVD, String ngayVD, String muctieu, String hoanthanh) {
        this.maVD = maVD;
        this.ngayVD = ngayVD;
        this.muctieu = muctieu;
        this.hoanthanh = hoanthanh;
    }

    public VanDongDTO() {
    }

    public String getMaVD() {
        return maVD;
    }

    public void setMaVD(String maVD) {
        this.maVD = maVD;
    }

    public String getNgayVD() {
        return ngayVD;
    }

    public void setNgayVD(String ngayVD) {
        this.ngayVD = ngayVD;
    }

    public String getMuctieu() {
        return muctieu;
    }

    public void setMuctieu(String muctieu) {
        this.muctieu = muctieu;
    }

    public String getHoanthanh() {
        return hoanthanh;
    }

    public void setHoanthanh(String hoanthanh) {
        this.hoanthanh = hoanthanh;
    }
}
