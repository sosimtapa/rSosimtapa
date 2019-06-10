package black.kr.hs.mirim.sosimtapaxml;

public class pBoard {

    private String id;
    private String userEditAddress;
    private String address;
    private String content;

    public pBoard(){
    }

    public pBoard(String id, String userEditAddress, String address, String content) {
        this.id = id;
        this.userEditAddress = userEditAddress;
        this.address = address;
        this.content = content;
    }

    public String getUserEditAddress() {
        return userEditAddress;
    }

    public void setUserEditAddress(String userEditAddress) {
        this.userEditAddress = userEditAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "pBoard{" +
                "id='" + id + '\'' +
                ", userEditAddress='" + userEditAddress + '\'' +
                ", address='" + address + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
