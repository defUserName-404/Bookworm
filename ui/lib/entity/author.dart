import 'package:flutter/foundation.dart';

@immutable
class Author {
  final String name;
  final String? description;

  const Author({
    required this.name,
    this.description,
  });

  @override
  String toString() {
    return 'name: $name description: $description';
  }

  factory Author.fromJson(Map<String, dynamic> json) => Author(
        name: json['name'] as String,
        description: json['description'] as String?,
      );

  Map<String, dynamic> toJson() => {
        'name': name,
        'description': description,
      };
}
