class ApiException implements Exception {}

final class AuthorNotFoundException extends ApiException {}

final class AuthorCreationFailedException extends ApiException {}

final class BookNotFoundException extends ApiException {}
