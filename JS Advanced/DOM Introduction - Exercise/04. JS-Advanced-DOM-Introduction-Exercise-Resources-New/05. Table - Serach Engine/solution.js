function solve() {
   document.querySelector('#searchBtn').addEventListener('click', onClick);
   let table = document.querySelectorAll('.container tr');

   function onClick() {
      let input = document.querySelector('#searchField').value;
      for (let row = 1; row < table.length; row++){
            if(table[row].textContent.toLowerCase().includes(input.toLowerCase())){
               table[row].classList.add('select');
            } else {
            table[row].classList.remove('select');
         }
      }
   }
}