# Getting Started with Create React App

This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

## Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.\
You will also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

### `npm run eject`

**Note: this is a one-way operation. Once you `eject`, you can’t go back!**

If you aren’t satisfied with the build tool and configuration choices, you can `eject` at any time. This command will remove the single build dependency from your project.

Instead, it will copy all the configuration files and the transitive dependencies (webpack, Babel, ESLint, etc) right into your project so you have full control over them. All of the commands except `eject` will still work, but they will point to the copied scripts so you can tweak them. At this point you’re on your own.

You don’t have to ever use `eject`. The curated feature set is suitable for small and middle deployments, and you shouldn’t feel obligated to use this feature. However we understand that this tool wouldn’t be useful if you couldn’t customize it when you are ready for it.

## Learn More

You can learn more in the [Create React App documentation](https://facebook.github.io/create-react-app/docs/getting-started).

To learn React, check out the [React documentation](https://reactjs.org/).

### Code Splitting

This section has moved here: [https://facebook.github.io/create-react-app/docs/code-splitting](https://facebook.github.io/create-react-app/docs/code-splitting)

### Analyzing the Bundle Size

This section has moved here: [https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size](https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size)

### Making a Progressive Web App

This section has moved here: [https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app](https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app)

### Advanced Configuration

This section has moved here: [https://facebook.github.io/create-react-app/docs/advanced-configuration](https://facebook.github.io/create-react-app/docs/advanced-configuration)

### Deployment

This section has moved here: [https://facebook.github.io/create-react-app/docs/deployment](https://facebook.github.io/create-react-app/docs/deployment)

### `npm run build` fails to minify

This section has moved here: [https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify](https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify)
=======
# User Stories
 - User can play the game and register score 
 - User can quit the game at anytime
 - User can learn more in the information page:
   - How to play
   - View company profiles
   - View the current leaderboard
 
Long 
- Stock price = $10
- buy 1 shares at $10/stock
- total cost = $10
- Market:
  - goes down - stock price $8
  - My initial $10 decrease in value to $8

Short
 - Stock price $10
 - Short 1 shares at $10/stock
 - total cost = $10
 - Market:
   - goes down - stock price $8
     - My initial $10 increases to $12
   - goes up - stock price $12
     - My initial $10 decrease to $8


# UML
## Models
### Game
    - int gameId
    - int userId
    - int lastYear - round number (10 rounds per game)
    - int currentMarketId
### Company
    - int companyId
    - String Name
    - int companyTypeId
    - int defaultStockPrice
    - String risk
        (Stock)
    - int stockTotal - total stocks available (start at 100)
    - int dividend - a value a user gets per stock, per comapny, per ye
### Market
    - int companyId
    - int Price - stock price, starts at 50
    - int YearNumber
    - int MarketId
    - int gameId
    - int StockPurchased
    - Boolean isLongInvestment - position (short or long)
    - Boolean isBankrupt
### MarketType
    - int marketId;
    - int roll - 1-20 random
    - int bullModify
    - int bearModify
    - int companyId
### Leaderboard
    - String username
    - int score

## Repository
### MarketTypeRepository
     int findRoll (int roll, int companyId, Boolean isBull)

### CompanyRepository
    List<Company> findAll()
    Company findById (int companyId)
    int findCompanyPriceById(int companyId)

### CompanyTypeRepository   
    String findCompanyTypeById (int companyTypeId)

### GameRepository
    Game findGameByUserID (int userId)
    Boolean addGameState (Game game)
    Boolean updateGameState (Game game)
    Boolean deleteGameState (int gameId)
[comment]: <> (Table needs UserId, CurrentMarket, yearNumber)

### UserRepository
    User FindByUserName (String Username)
    Boolean addUser (String username)
    Boolean deleteUser (userId)

### LeaderboardRepository
    List<LeaderBoard> findAll()
    Boolean addHighScore(String username, int score)

### MarketRepository   
    List<Market> findByGameId(int gameId)
    List<Market> findPortfolio(int gameId, int yearNumber)
    List<Market> findByCompanyId (int companyId, int gameId)
    boolean addMarket (Market market)
    boolean setBankrupt(boolean bankrupt, int gameId) - handle checking removal of a comapny in the market?
    boolean deleteMarket (int gameId) - remove company for bankruptcy
    boolean addCompany (int companyId, int gameId) - adds if less than 10 companies in market

### MarketTypeRepository
    MarketType findRoll(int roll, int companyId)

## Service

### MarketTypeService
     int findRoll (int roll, int companyId, Boolean isBull)

### CompanyService
    List<Company> findAll()
    Company findById (int companyId)
    int readCompanyPriceById(int companyId)

### CompanyTypeService
    String findCompanyTypeById (int companyTypeId)

### GameService
    Game findGameByUserID (int UserId)
    Boolean addGameState (Game game)
    Boolean updateGameState (Game game)
    Boolean deleteGameState (int userId)
        - Boolean Verify (Game game)
[comment]: <> (Table needs UserId, yearNumber)
[comment]: <> (Everything above needs to be filled ei String username, Market market, int yearNumber)

### LeaderboardService
    List<Leaderboard> findAll()
    Boolean addHighScore(String username, int Score)

### MarketService
    List<Market> findByGameId(int gameId)
    List<Market> findPortfolio(int gameId, int yearNumber)
    List<Market> findByCompanyId (int companyId, int gameId)
    Boolean addMarket (Market market)
    Boolean setBankrupt(boolean bankrupt) - handle checking removal of a comapny in the market?
    Boolean deleteMarket (userId) - remove company for bankruptcy
    Boolean addCompany (int companyId) - adds if less than 10 companies in market

## Controller
### MarketTypeController
    @GETMAPPING
    int findRoll()

### CompanyController
    @GETMAPPING
    List<Company> findAll()
    @GETMAPPING
    Company findById (int companyId)
    @GETMAPPING
    int readCompanyPriceById (int companyId)
    @GETMAPPING
    int findStockAvailable (int companyId)

### CompanyTypeController
    @GETMAPPING
    String findCompanyTypeById (companyTypeId)

### GameController
    @GETMAPPING
    Game findGameByUserID (UserId)
    @POSTMAPPING
    Boolean addGameState (Game game)
    @PUTMAPPING
    Boolean updateGameState (Game game)
    @PUTMAPPING
    Boolean quitGame (Game game)
[comment]: <> (Table needs UserId, PortfolioId, Market, MarketType, yearNumber)

### LeaderboardController
    @GETMAPPING
    List<Leaderboard> findAll()
    @POSTMAPPING
    Boolean addHighScore(String username, int score)

### MarketController
    @GETMAPPING
    List<Market> findAll(int gameId)
    @GETMAPPING
    Market findByGameId (int gameId)
    @GETMAPPING
    List<Market> findPortfolio (int yearNumber, int GameId)
    @GETMAPPING
    List<Market>findByCompanyId (int companyId, int gameId) 
    @POSTMAPPING
    boolean addMarket (Market market)
    @PUTMAPPING
    boolean setBankrupt(boolean bankrupt)
    @DELETEMAPPING
    boolean deleteMarket (userId)
    @POSTMAPPING
    boolean addCompany (int companyId)

### Play
- Button audio toggle

#### createPortfolio
    - Input Companies, stock #/ company
    - Cancel() - Link to playMenu
    - Output Portfolio Object

#### playMenu
    - View ("Input" Game.Portfolio.companies) "Output" Display Portfolio
    - View ("Input" Game.Market.companies) "Output" Display Market

#### View
    - Return Table of Companies

### Information
    - ViewLeaderboard
    - ViewTutorial
    - ViewCompanies
    - Bug Report 

### ViewLeaderboard
    - Table Ranks
### ViewTutorial
    - Hard Code Tutorial
### ViewCompanies
    - Table Companies
### Bug Report
    - Inputs Username String, Subject String, Description String
    - Return Success/Failure

### Quit
    - Return to home

# WireFrames
    - See wireframe.jpgs

# Database Schema
     - See ERD diagram
