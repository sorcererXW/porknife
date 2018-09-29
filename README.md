[![](https://jitpack.io/v/sorcererxw/porknife.svg)](https://jitpack.io/#sorcererxw/porknife)

A tool to parse podcast rss.

# Install

## Gradle

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
  implementation 'com.github.sorcererxw:porknife:{lastest_release_version}'
}
```

## Maven

```
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependency>
  <groupId>com.github.sorcererxw</groupId>
  <artifactId>porknife</artifactId>
  <version>{lastest_release_version}</version>
</dependency>
```

# Usage

```
new Porknife().parse(url)
```
