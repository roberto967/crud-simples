# Escolha uma imagem base que possua o Node.js instalado
FROM node

# Diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar os arquivos de package.json e package-lock.json para o diretório de trabalho
COPY package*.json ./

# Instalar as dependências do projeto
RUN npm install

# Copiar todos os arquivos do diretório do aplicativo React para o diretório de trabalho
COPY . .

# Build do aplicativo React
RUN npm run build

# Comando para executar o aplicativo React
CMD [ "npm", "start" ]