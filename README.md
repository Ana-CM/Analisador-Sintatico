# Trabalho-de-Compiladores

Analisador sintático utilizando Canopy para a linguagem lang.

## Requisitos
Os fontes foram desenvolvidos na versão do java 11.0.15, sendo assim é recomendado que você utilize a mesma versão ou superior

## Execução

No projeto existem dois arquivos de build, um que permite somente a execução do programa e outro que permite a recompilação do programa gerando assim novamente os arquivos pelo Canopy.

Caso queira somente executar o programa,  sem gerar, basta executar o comando:

```
bash build-parser-no-canopy.sh
```

Mas caso queira gerar novamente os arquivos,  é necessário instalar o Canopy na sua máquina utilizando os seguintes comando:

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

Existem três funcionalidades implementadas. O usuário pode executar uma bateria de testes sintáticos, fazer a interpretação de um arquivo em específico ou gerar um arquivo .graphviz para visualizar a AST de um arquivo em específico. 

Para realizar a bateria de testes:

```
java LangCompiler -bs
```

Para interpretar uma arquivo:

```
java LangCompiler -i [caminho_do_arquivo]
```

Para gerar o arquivo .graphviz:

```
java LangCompiler -gvz [caminho_do_arquivo]
```