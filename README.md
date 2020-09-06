# GOOSE GAME KATA
Goose game kata implemented in java.

## Build

Into the root folder of the project


```bash
mkdir -p ./out ./dist

javac -cp ./src ./src/goose/game/fb/*.java ./src/goose/game/fb/**/*.java -d ./out/

jar cvfm ./dist/Goose-Game-Fb.jar ./manifest.mf -C ./out/ .
```


## Run

```bash
java -jar ./dist/Goose-Game-Fb.jar
```
