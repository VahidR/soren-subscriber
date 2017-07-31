package org.vahid.db;

import org.skife.jdbi.v2.sqlobject.*;

import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.vahid.api.Category;
import org.vahid.api.FavoriteBook;
import org.vahid.api.MapEntity;

import java.util.List;

/**
 * Created by vahid (@vahid_r)
 */

public interface SorenSubscriberDAO extends AutoCloseable {

    @SqlUpdate("insert into book(title, categoryCodes) values(:title, :categoryCodes)")
    @GetGeneratedKeys
    long createBook(@Bind("title") String title, @Bind("categoryCodes") String categoryCodes);


    @SqlUpdate("insert into category(code, title, superCategoryCode) values(:code, :title, :superCategoryCode)")
    @GetGeneratedKeys
    long createCategory(@BindBean Category category);


    @SqlUpdate("insert into subscriber(email, categoryCodes) values(:email, :categoryCodes)")
    @GetGeneratedKeys
    long createSubscriber(@Bind("email") String email, @Bind("categoryCodes") String categoryCodes);


    @SqlQuery("with recursive ancestry (code, title, superCategoryCode, path) as ( " +
            "select code, title, superCategoryCode, title as path from category where superCategoryCode is null " +
            "union all " +
            "select c.code, c.superCategoryCode, c.title, (a.path || '->' || c.title) " +
            "from category c, ancestry a where c.superCategoryCode = a.code) " +
            "select path from ancestry where code = :name; ")
    String getCategoryPath(@Bind("name") String name);


    @SqlQuery("select title from category where code = :code")
    String getCategoryName(@Bind("code") String code);


    @SqlUpdate("insert into category_book(category_path, book) values(:category_path, :book)")
    void insertIntoCategoryBookTable(@Bind("category_path") String category_path, @Bind("book") String book);


    @SqlQuery("select email, categoryCodes from subscriber")
    @Mapper(MapMapper.class)
    List<MapEntity> getSubscribersWithCats();

    @SqlQuery("select category_path, book from category_book where category_path ~ :category_path or category_path ~ (:category_path || '->');")
    @Mapper(NewsLetterMapper.class)
    List<FavoriteBook> getPathAndBookForThisUser(@Bind("category_path") String category_path);

    void close();

}
