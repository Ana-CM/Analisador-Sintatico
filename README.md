# Trabalho-de-Compiladores

Analisador sintático utilizando Canopy para a linguagem lang.

## Requisitos
Os fontes foram desenvolvidos na versão do java 11.0.15, sendo assim é recomendado que você utilize a mesma versão ou superior

## Execução

No projeto existem dois arquivos de build, um que permite somente a execução do programa e outro que permite a re-compilação do programa gerando assim novamente os arquivos pelo Canopy.

Caso queira somente executar o programa,  sem gerar, basta executar o comando:

```
bash build-parser-no-canopy.sh
```

Mas caso queria gerar novamente os arquivos,  é necessário instalar o Canopy na sua máquina utilizando os seguintes comando:

```
sudo apt update
sudo apt install npm
npm install -g canopy
```

e após, executar o comando:

```
bash build-parser.sh
```

## Testes

Para os testes está sendo utilizada a pasta testes/sintaxe/certo/, onde estão os arquivos de teste que devem ser compilados com sucesso.
Caso deseje realizar testes em outras pastas basta alterar o diretório de testes no arquivo lang/parser/TestParser.java.