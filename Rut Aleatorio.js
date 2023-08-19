
const rut = generarRut();
console.log(rut);

function generarRut() {
  const generarNumeros = Array.from({ length: 8 }, () => Math.floor(Math.random() * 10));
  const digVerificador = calcularVerificador(generarNumeros);
  
  const formato = `${generarNumeros.slice(0, 2).join('')}.${generarNumeros.slice(2, 5).join('')}.${generarNumeros.slice(5).join('')}-${digVerificador}`;
  
  return formato;
}

function calcularVerificador(generarNumeros) {
  const secVerificador = [2, 3, 4, 5, 6, 7, 2, 3];
  let suma = 0;
  
  for (let i = 0; i < generarNumeros.length; i++) {
    suma += generarNumeros[generarNumeros.length - 1 - i] * secVerificador[i];
  }
  
  const resto = suma % 11;
  const verificador = 11 - resto;
  
  return (verificador === 10) ? 'K' : (verificador === 11) ? '0' : verificador.toString();
}


