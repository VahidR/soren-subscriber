-- creating table book
DROP TABLE IF EXISTS book;
CREATE TABLE book(
  id SERIAL PRIMARY KEY,
  title TEXT UNIQUE NOT NULL,
  categoryCodes TEXT NOT NULL
);
CREATE INDEX book_index ON book (title, categoryCodes);


-- creating table subscriber
DROP TABLE IF EXISTS subscriber;
CREATE TABLE subscriber(
  id SERIAL PRIMARY KEY,
  email TEXT UNIQUE NOT NULL,
  categoryCodes TEXT NOT NULL
);
CREATE INDEX subscriber_index ON subscriber (email, categoryCodes);


-- creating table subscriber
DROP TABLE IF EXISTS category;
CREATE TABLE category(
  id SERIAL PRIMARY KEY,
  code TEXT UNIQUE NOT NULL,
  title TEXT UNIQUE NOT NULL,
  superCategoryCode TEXT
);
CREATE INDEX category_index ON category (code, title, superCategoryCode);


-- Creating table category_book
DROP TABLE IF EXISTS category_book;
CREATE TABLE category_book(
  id SERIAL PRIMARY KEY,
  category_path TEXT NOT NULL,
  book TEXT NOT NULL
);
CREATE INDEX category_book_index ON category_book(category_path, book);

