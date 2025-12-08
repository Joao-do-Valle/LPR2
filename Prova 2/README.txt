Trabalho prático – Substitui PII
Projeto Java Swing + SQLite (simples) para cadastro de pacientes.

Conteúdo:
- Database.java         : Classe para comunicar com o banco SQLite (JDBC).
- PatientApp.java       : Interface Swing com os botões exigidos.
- schema.sql            : Script para criar a tabela (opcional, o app cria automaticamente).
- README.txt            : Este arquivo.

Requisitos:
- Java 8+ (javac/java)
- driver SQLite JDBC: sqlite-jdbc-(VERSAO).jar
  Baixe de: https://github.com/xerial/sqlite-jdbc/releases (escolha a versão mais recente)
  Coloque o JAR na mesma pasta ao compilar/rodar ou em seu classpath.

Como compilar (exemplo usando sqlite-jdbc-3.36.0.3.jar):
1) Coloque sqlite-jdbc-*.jar em /mnt/data/patient_app/ ou na pasta atual.
2) Compile:
   javac -cp ".:sqlite-jdbc-3.36.0.3.jar" Database.java PatientApp.java
   (Em Windows use ; em vez de : no classpath)

Como executar:
   java -cp ".:sqlite-jdbc-3.36.0.3.jar" PatientApp
   (Em Windows ajuste classpath com ;)

O programa usa um arquivo de banco 'patients.db' criado automaticamente na pasta onde rodar.

Funcionalidades implementadas:
- Campos: ID (auto), Nome, Idade, Peso, Altura.
- Botões:
  * Incluir: valida tipos (int/float) e insere no banco.
  * Limpar: limpa campos (ID não é editável).
  * Apresenta Dados: mostra todos os pacientes em um JOptionPane.
  * Pesquisar: pede um nome (usa LIKE) e apresenta os resultados.
  * Créditos: abre um painel com nomes da dupla.
  * Sair: fecha o app.

Observações:
- ID é gerado automaticamente pelo banco (AUTOINCREMENT).
- Caso não queira usar JDBC/SQLite, você pode adaptar o Database.java para outro DB (MySQL, etc.) e alterar a URL/driver.
- Testado em Java 11.

Boa sorte — entregue o trabalho!
