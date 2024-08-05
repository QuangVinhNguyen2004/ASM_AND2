package com.example.asmand2_vinhnqph45928.DTO;

public class ThongTinDTO {
    private String id;
    private String gioitinh;
    private int chieucao;
    private int cannang;

    public ThongTinDTO(String id, String gioitinh, int chieucao, int cannang) {
        this.id = id;
        this.gioitinh = gioitinh;
        this.chieucao = chieucao;
        this.cannang = cannang;
    }

    public ThongTinDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public int getChieucao() {
        return chieucao;
    }

    public void setChieucao(int chieucao) {
        this.chieucao = chieucao;
    }

    public int getCannang() {
        return cannang;
    }

    public void setCannang(int cannang) {
        this.cannang = cannang;
    }
}
