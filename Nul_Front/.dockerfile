# ---------- Étape 1 : build ----------
FROM node:22-alpine AS build
WORKDIR /app

COPY package*.json ./
RUN npm ci

COPY . .
RUN npm run build

# Ne garder que les dépendances de production
RUN npm prune --omit=dev

# ---------- Étape 2 : runtime ----------
FROM node:22-alpine AS runtime
WORKDIR /app

ENV NODE_ENV=production
ENV PORT=3000
ENV HOST=0.0.0.0

COPY --from=build /app/build ./build
COPY --from=build /app/node_modules ./node_modules
COPY --from=build /app/package.json ./package.json

# Exécuter en utilisateur non-root
USER node

EXPOSE 3000
CMD ["node", "build"]