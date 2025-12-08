-- schema.sql: cria a tabela patients (SQLite)
CREATE TABLE IF NOT EXISTS patients (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  nome TEXT NOT NULL,
  idade INTEGER,
  peso REAL,
  altura REAL
);
