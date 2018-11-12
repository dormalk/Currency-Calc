# Currency Converter
  Java app can converet from one value of currency to another one
 
## Get started
  Run on local machine with Eclipse (Never get production).
  The conversion available only for those currencies
  	USD, GBP, JPY, EUR, AUD, CAD, DKK, NOK,
	  ZAR, SEK, CHF, JUD, LBP, EGP, ILS.

## Components
### CalculatorGUI 
	Client visual view
		- Present CurrenciesTable for all currency
### TableGUI 
	Visual table view for GUI
### CurrencyTable
	Abstract tabel, Holds all the information on TableGUI
### Currency
	Instruction for currency, holds fields:
		name, unit, code, country, change
### XmlParse
	Hendle Parsing information that came from local storage
		- xml file name is 'currency.xml'
		- xml file holds data on currencies
		- every opening, the app download relevant version of xml

## Project Status
	Done
		
  
