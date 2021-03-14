const { chromium } = require('playwright-chromium');
const assert = require('chai').assert;

let browser;
let page;

describe('E2E tests', async ()=> {
    before(async () => { browser = await chromium.launch(); });
    after(async () => { await browser.close(); });
    beforeEach(async () => { page = await browser.newPage(); });  
    afterEach(async () => { await page.close(); });
    it('Load all messages', async ()=>{
        await page.goto('http://localhost:3000/');
        await page.click('text=Refresh');
        let msgs = await page.$$eval('textarea',(tx)=>tx.map(tx=>tx.value));
        assert.include(msgs[0],'Spami: Hello, are you there');
    })
    it('Test sending a new message', async ()=>{
        await page.goto('http://localhost:3000/');
        await page.fill('#author', 'Kiro');
        await page.fill('#content', 'Zdravei');
        await page.click('text=Send');
        await page.click('text=Refresh');

        let msgs = await page.$$eval('textarea',(tx)=>tx.map(tx=>tx.value));
        assert.include(msgs[0],'Kiro: Zdravei');
    })
    });