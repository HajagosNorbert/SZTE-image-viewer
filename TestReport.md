# Tesztjegyzőkönyv

## Összefoglalás:
A csapatunk által létrehozott képnézegető program sikeresen elkészült a februártól májúsig tartó munkálatok alatt.
Először egy vizuális dokumentáció készült el (UML) és ennek mintájára valósult meg az alkalmazás a JavaFX szoftverplatform segítségével.

### Általános értékelés:
A program az elvárt feltételeknek megfelel.

### Teszt eredmények összegzése:

**1. Teszt** <br>
- Kép beolvasása: sikeres
- Kép mentése: sikeres

**2. Teszt** <br>
- Kép beolvás: sikeres
- Forgatás balra: sikeres
- Forgatás jobbra: sikeres
- Kép mentése: sikeres(a kép megfelelő dőlésszögben került mentésre)

**3. Teszt** <br>
- Kép beolvasása: sikeres
- Forgatás jobbra: sikeres
- ColorScale ikonra kattintással a sidebar eltüntetése: sikeres
- ColorScale ikonra kattintással a sidebar láthatóvá tétele: sikeres
- RGB színváltoztatás RGB(100,100,100)-ról RGB(200,0,255)-re (ezzel a fehérből lila lett): sikeres
- Kép mentése: sikeres!
- Színezett kép mentése: sikeres
- A mentett kép újra megnyitása: sikeres

**4. Teszt** <br>
- Kép beolvasása: sikeres
- Forgatás jobbra: sikeres
- Nagyítás: sikeres
- Nagyított kép mozgatása: sikeres
- Kép mentéses (A képet megfelelően nem a nagyított változatban mentette, hanem a nagyítatlan képet): sikeres
- Nagyított állapot közben forgatás: sikeres
- A kép újraméretezése az eredeti formába: sikeres

**5. Teszt** <br>
- Kép beolvasása: sikeres
- Kép invertálása (ColorScale-en keresztül): sikeres
- Kép forgatása 4x jobbra (ezzel az eredeti dőlésszögbe került): sikeres
- Edit->Rotate left(másik forgatási lehetőség): sikeres



### Előrehaladás:
    
Az előrehaladás fokozatosan történt. Kezdetben több energiát fektetett a csapat a projektbe az új tudás elsajátítása érdekében, ekkor a projekt jelentős része kész lett, 
a továbbiakban komplexebb problémák (pl. imageView helyett az Imagen történjen a változatás) kerültek javításra. Valamint kisebb hiányzó elemeket lettek megvalósítva, végül a tesztesetek kerültek megírásra.

    
