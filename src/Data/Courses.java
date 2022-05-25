package Data;

import java.io.Serializable;

public class Courses implements Serializable{
    private String name, description;
    private int n_cupos, n_credits, n_class;

    public Courses(String name, String description, int n_class, int n_cupos, int n_credits) {
        this.name = name;
        this.description = description;
        this.n_class = n_class;
        this.n_cupos = n_cupos;
        this.n_credits = n_credits;
    }
    public Courses(){ }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getN_cupos() {
        return n_cupos;
    }
    public void setN_cupos(int n_cupos) {
        this.n_cupos = n_cupos;
    }
    public int getN_credits() {
        return n_credits;
    }
    public void setN_credits(int n_credits) {
        this.n_credits = n_credits;
    }
    public int getN_class() {
        return n_class;
    }
    public void setN_class(int n_clase) {
        this.n_class = n_clase;
    }
}
