package fr.enedis.grafana.dsl.panels.imageIt

class ImageItDisplayOptionsBuilder {

    var imageUrl: String = "https://i.ibb.co/tLXrjb6/imageit.png"
    var forceImageRefresh: Boolean = false
    var lockSensors: Boolean = false
    var sensorsTextSize: Int = 10
    var sensors: List<String> = emptyList()
    var mappings: List<String> = emptyList()


    fun createImageItDisplayOptions(): ImageItDisplayOptions = ImageItDisplayOptions(
        imageUrl = imageUrl,
        forceImageRefresh = forceImageRefresh,
        lockSensors = lockSensors,
        sensorsTextSize = sensorsTextSize,
        sensors = sensors,
        mappings = mappings
    )

}
