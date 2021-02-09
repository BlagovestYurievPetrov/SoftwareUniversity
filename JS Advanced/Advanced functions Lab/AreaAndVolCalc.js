function solve (area, vol, input) {
    return JSON.parse(input).map(x => ({
        area: area.call(x),
        volume: vol.call(x)
    }));
}
