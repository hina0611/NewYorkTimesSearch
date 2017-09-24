package com.search.hinaikhan.newyorktimessearch.util;

import java.util.Date;
import java.util.List;

/**
 * Created by hinaikhan on 9/24/17.
 */

public class SearchSettings {

    public static final String CATEGORY_ARTS = "Arts";
    public static final String CATEGORY_FASHION_AND_STYLE = "Fashion & Style";
    public static final String CATEGORY_SPORTS = "Sports";

    public static final String SORT_OLDEST = "Oldest Item";
    public static final String SORT_NEWEST = "Newest Item";


    private List<String> categories;
    private String sortOrder;
    private Date beginDate;

    public SearchSettings() {

    }

    public SearchSettings(List<String> categories, String sortOrder, Date beginDate) {
        this.categories = categories;
        this.sortOrder = sortOrder;
        this.beginDate = beginDate;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public String buildFqQuery() {
        StringBuilder sb = new StringBuilder();

        if (categories != null && !categories.isEmpty()) {
            sb.append("news_desk:(");
            for (String category : categories) {
                sb.append("\"").append(category).append("\" ");
            }
            sb.append(")");
        }

        return sb.toString();
    }

    public boolean hasSelectedCategory(String category) {
        if (category != null && categories != null) {
            for (String cat : categories) {
                if (cat.equalsIgnoreCase(category)) {
                    return true;
                }
            }
        }
        return false;
    }
}
