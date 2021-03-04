async function attachEvents() {
    let response = await fetch(`http://localhost:3030/jsonstore/forecaster/locations`);
    let data = await response.json();
    let button = document.getElementById('submit');
    let location = document.getElementById('location');
    let fore = document.getElementById('forecast');
    let current = document.getElementById('current');
    button.addEventListener('click',async ()=>{
        fore.style.display = 'block';
        try{
        let found = data.find(x=>x.name === location.value);
        let code = found.code;
        let forecastResponse = await fetch(`http://localhost:3030/jsonstore/forecaster/today/${code}`);
        let forecast = await forecastResponse.json();
        console.log(forecast);
        console.log(forecast.forecast.condition);
        console.log(forecast.forecast.low);


        let threeDayResponse = await fetch(`http://localhost:3030/jsonstore/forecaster/upcoming/${code}`);
        let threeDayForecast = await threeDayResponse.json();
        console.log(threeDayForecast);

        //Generate Today's forecast HTML
        // Condition symbol
        let div = document.createElement('div');
        div.className = 'forecasts';
        let conSymbol = document.createElement('span');
        conSymbol.className = 'condition symbol';
        switch(forecast.forecast.condition){
            case 'Sunny':
                conSymbol.innerHTML = `${`\&#x2600`}`;
                break;
            case 'Partly sunny':
                conSymbol.innerHTML = `${'\&#x26C5'}`;
                break;
            case 'Overcast':
                conSymbol.innerHTML = `${'\&#x2601'}`;
                break;
            case 'Rain':
                conSymbol.innerHTML = `${'\&#x2614'}`;
                break;
        }
        
        let span = document.createElement('span');
        span.className = 'condition';
        let span2 = document.createElement('span');
        span2.className = 'forecast-data';
        span2.textContent = forecast.name;
        let span3 = document.createElement('span');
        span3.className = 'forecast-data';
        span3.innerHTML = `${forecast.forecast.low}${`\&#176/`}${forecast.forecast.high}&#176`;
        let span4 = document.createElement('span');
        span4.className = 'forecast-data';
        span4.textContent = forecast.forecast.condition;

        span.appendChild(span2);
        span.appendChild(span3);
        span.appendChild(span4);
        div.appendChild(conSymbol);
        div.appendChild(span);
        current.appendChild(div);

        //Generate forecast HTML for three days

        let mainDiv = document.getElementById('upcoming');
        let secDiv = document.createElement('div');
        secDiv.className = 'forecast-info';
        threeDayForecast.forecast.map((x)=>{
            let mainSpan = document.createElement('span');
            mainSpan.className = 'upcoming';
            let symSpan = document.createElement('span');
            symSpan.className = 'symbol';
            switch(x.condition){
                case 'Sunny':
                    symSpan.innerHTML = `${`\&#x2600`}`;
                    break;
                case 'Partly sunny':
                    symSpan.innerHTML = `${'\&#x26C5'}`;
                    break;
                case 'Overcast':
                    symSpan.innerHTML = `${'\&#x2601'}`;
                    break;
                case 'Rain':
                    symSpan.innerHTML = `${'\&#x2614'}`;
                    break;
            }
            let tempSpan = document.createElement('span');
            tempSpan.className = 'forecast-info';
            tempSpan.innerHTML = `${x.low}${`\&#176/`}${x.high}&#176`;
            let condSpan = document.createElement('span');
            condSpan.className = 'forecast-info';
            condSpan.innerHTML = x.condition;
            mainSpan.appendChild(symSpan);
            mainSpan.appendChild(tempSpan);
            mainSpan.appendChild(condSpan);
            secDiv.appendChild(mainSpan);
            mainDiv.appendChild(secDiv);

        })
        console.log(threeDayForecast);
        }catch(err){
            fore.textContent = 'Error';
        }
        
    })
}

attachEvents();