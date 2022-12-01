## data-class-converter

This tool is used to convert various `data-class` (kotlin) definitions to other types and languages.

*It is in early stage.*

We plan to add several converters in the future:
- `Kotlin data class` to `Scala case class`
- `Protobuf` to `Kotlin data class`
- Maybe we will support `rust`.

---

### Converters

Currently, the following converters are supported.

- `Java` to `Koltin`
    - `record`
    - `enum class`

###  

### Usage

```
usage: java -jar java-to-kotlin
 -op,--output-path <arg>    Specifies the output directory path.
 -pn,--package-name <arg>   Specifies the package name of the all
                            generated classes.
 -sp,--scan-package <arg>   Specifies the search path. All classes in this
                            package will be transformed.
```