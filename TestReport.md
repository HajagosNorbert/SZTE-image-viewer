# Tesztjegyzőkönyv


    Összefoglalás: 
    A csapatunk által létrehozott képnézegető program sikeresen elkészült a februártól májúsig tartó munkálatok alatt.
    Eleinte egy UML diagrammon gondoltuk át a program struktúráját, később megvalósítottuk a működést Java nyelven a JavaFx szoftverplatform segítségével.
    

    Általános értékelés:
    A program teljesítette az összes elvárt feltételt. 
    
    Teszt eredmények összegzése:
    
    1. teszt
    Kép beolvasása: sikeres
    Kép mentése: sikeres
    
    2. teszt
    Kép beolvás:sikeres
    Forgatás balra: sikeres
    Forggatás balra: sikeres
    Forgatás jobbra: sikeres
    Kép mentése: sikeres(a kép megfelelő dőlésszögmen mentődött)
    
    3. teszt
    Kép beolvasása: sikeres
    Forgatás jobbra: sikeres
    Color Scale ikonra kattintással a sidebar eltüntetése: sikeres
    Color Scale ikonra kattintással a sidebar láthatóvá tétele: sikeres
    RGB színváltoztatás RGB(100,100,100)-ról RGB(200,0,255)-re (ezzel a fehérből lila lett): sikeres
    Kép mentése: sikeres !Nem lila árnyalatban mentette hanem kékben(sötétebb)!-- HIBA
    A mentett kép újra megnyitása: sikeres
    
    4. teszt
    Kép beolvasása: sikeres
    Forgatás jobbra: sikeres
    Nagyítás: sikeres
    Nagyított kép mozgatása: siekres
    Kép mentéses (A képet megfelelően nem a nagyított változatban mentette, hanem a nagyítatlan képet): sikeres
    Nagyított állapot közben forgatás: !A kép 2 forgatásra helyesen működik, páratlan számúra nem fordul a keret a képpel!-- HIBA 
    A kép újraméretezése az eredeti formába: sikeres
    
    5. teszt
    Kép beolvasása: sikeres
    Color scale invertálás: sikeres
    Kép forgatása 4x jobbra (ezzel az eredeti dőlésszögbe került): sikeres
    Edit->Rorate left(másik forgatási lehetőség): sikeres
    
    

    Előrehaladás:
    
    Az előrehaladás fokozatosan történt. Eleinte több energiát fektetett a projektbe a csapat az új tudás elsajátítása érdekében, ekko a projekt nagy része kész lett, 
    a továbbiakban komplexebb problémák(pl. imageview helyett az imagen történjen a változatás) kerültek javításra. Valamint kisebb hiányzó elemek lettek bepótolva, végül a tesztesetek lettek megírva.

    
