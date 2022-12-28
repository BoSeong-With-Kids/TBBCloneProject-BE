package com.team1.TBBCloneCoding.project.entity;

import lombok.Getter;
public enum ProjectCategoryEnum {
    All("all"),
    BOARDGAME("boardGame"),
    DIGITALGAME("digitalGame"),
    WEBTOONCARTOON("webtoonCartoon"),
    WEBTOONRESOURCE("webtoonResource"),
    DESIGNWORD("designWord"),
    CHARACTERGOOD("characterGood"),
    HOMELIVING("homeLiving"),
    TECHELECTRONIC("techElectronic"),
    MYPET("myPet"),
    PERFUMEBEAUTY("perfumeBeauty"),
    CLOTHES("clothes"),
    THING("thing"),
    JEWEL("jewel"),
    PUBLISHING("publishing"),
    DESIGN("design"),
    ART("art"),
    PICTURE("picture"),
    MUSIC("music"),
    MOVIE("movie"),
    SHOW("show");
    private final String category;

    ProjectCategoryEnum(final String category){
        this.category = category;
    }
}