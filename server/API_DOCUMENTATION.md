# Bookworm application API Documentation

- **Base URL**: `/api/v1`

## Books

### Get All Books

- **Endpoint**: `/books`
- **Method**: `GET`
- **Description**: Retrieve a list of all books in the collection.

### Get Book by ID

- **Endpoint**: `/books/{id}`
- **Method**: `GET`
- **Description**: Retrieve details of a specific book by its ID.

### Add a New Book

- **Endpoint**: `/books`
- **Method**: `POST`
- **Description**: Add a new book to the bookstore.
- **Request Body**:
    ```jsMin
    {
      "title": "The Great Gatsby",
      "authors": [
        {
          "name": "F. Scott Fitzgerald"
        }
      ]
      // other book fields
    }
    ```

### Update a Book

- **Endpoint**: `/books/{id}`
- **Method**: `PUT`
- **Description**: Update an existing book.
- **Request Body**:
    ```jsMin
    {
      "title": "Updated Title"
      // other updated fields
    }
    ```

### Delete a Book

- **Endpoint**: `/books/{id}`
- **Method**: `DELETE`
- **Description**: Delete a book from the bookstore.

## Authors

### Get All Authors

- **Endpoint**: `/authors`
- **Method**: `GET`
- **Description**: Retrieve a list of all authors in the bookstore.

### Get Author by ID

- **Endpoint**: `/authors/{id}`
- **Method**: `GET`
- **Description**: Retrieve details of a specific author by their ID.

### Add New Authors

- **Endpoint**: `/authors`
- **Method**: `POST`
- **Description**: Add new authors to the bookstore.
- **Request Body**:
    ```jsMin
    [
      {
        "name": "Author1"
      },
      {
        "name": "Author2"
      }
      // other author fields
    ]
    ```

### Update an Author

- **Endpoint**: `/authors/{id}`
- **Method**: `PUT`
- **Description**: Update details of an existing author.
- **Request Body**:
    ```jsMin
    {
      "name": "Updated Author Name"
      // other updated fields
    }
    ```

### Delete an Author

- **Endpoint**: `/authors/{id}`
- **Method**: `DELETE`
- **Description**: Delete an author from the bookstore.
