package com.example.uppercutu.firebase

object FirestoreImageManager {

    /**
     * URI de la imagen temporal.
     */
    private var temporaryImageUri: String? = null

    /**
     * Obtiene la URI de la imagen temporal.
     *
     * @return La URI de la imagen temporal.
     */
    fun getTemporaryImageUri(): String? {
        return temporaryImageUri
    }

    /**
     * Establece la URI de la imagen temporal.
     *
     * @param uri La URI de la imagen temporal.
     */
    fun setTemporaryImageUri(uri: String?) {
        temporaryImageUri = uri
    }

    /**
     * Verifica si la URI de la imagen temporal está vacía o nula.
     *
     * @return `true` si la URI de la imagen temporal está vacía o nula, `false` de lo contrario.
     */
    fun isTemporaryImageUriEmpty(): Boolean {
        return temporaryImageUri.isNullOrEmpty()
    }

    /**
     * Borra la URI de la imagen temporal, estableciéndola como nula.
     */
    fun clearTemporaryImageUri() {
        temporaryImageUri = null
    }
}