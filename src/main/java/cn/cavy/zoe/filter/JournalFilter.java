package cn.cavy.zoe.filter;

public class JournalFilter {

    String title;

    Integer categoryId;

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getTitle() {
        return "%" + title + "%";
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
