// Importar la biblioteca boxrec
const boxrec = require('boxrec');

// Llamar a la función getChampions
boxrec.Boxrec.getChampions().then(champions => {
    // Imprimir los campeones en la consola
    console.log(champions);
}).catch(error => {
    // Manejar errores si ocurren
    console.error('Error al obtener los campeones:', error);
});
