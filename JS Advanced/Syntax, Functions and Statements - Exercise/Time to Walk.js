
function walk(stepCount,stepLengthInM,speedInKmH) {
    // distance in meters
    const distance = stepCount*stepLengthInM;
    const speedInMS = speedInKmH/3.6;
    const numOfBreaks = Math.floor(distance/500);
    const initialSeconds = distance/speedInMS;
    const lastSeconds = initialSeconds + (numOfBreaks*60);
    // For Printing
    let printingSeconds = lastSeconds;
    let printingMins = 0 ;
    let printingHours = 0;
    if (printingSeconds>59){
      printingMins = Math.floor(printingSeconds/60);
      printingSeconds = printingSeconds%60;
    }
    if (printingMins>59){
      printingHours = Math.floor(printingMins/60);
      printingMins = printingMins%60;
    }
    // LeadingZero?
    function leadingZero(time){
      if (time<10){
        return `0${time}`;
      }
      return `${time}`;
    }
    // Printing 
    console.log(`${leadingZero(printingHours)}:${leadingZero(printingMins)}:${leadingZero(printingSeconds.toFixed(0))}`)
    
  
  }