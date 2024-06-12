# Stage 1: Build the React app
FROM node:14-alpine as build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# Stage 2: Serve the React app using `serve`
FROM node:14-alpine
WORKDIR /app

# Install `serve` globally
RUN npm install -g serve

# Copy the build output from the previous stage
COPY --from=build /app/build ./build

# Expose port 5000 (default port for `serve`)
EXPOSE 5000

# Use `serve` to serve the static files
CMD ["serve", "-s", "build"]
