public class Scorer {
    private String name;
    private String info;
    private Integer point;

    public Scorer(Integer point, String name, String info) {
        this.name = name;
        this.info = info;
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}
