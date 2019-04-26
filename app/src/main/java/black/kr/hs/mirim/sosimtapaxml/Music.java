package black.kr.hs.mirim.sosimtapaxml;

public class Music {

    private int img;
    private String name;
    private String singer;
    private int song;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public Music(int img, String name, String singer, int song) {
        this.img = img;
        this.name = name;
        this.singer = singer;
        this.song = song;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public int getSong() {
        return song;
    }

    public void setSong(int song) {
        this.song = song;
    }
}
