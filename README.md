# User Stories
 - User can play the game and register score 
 - User can quit the game at anytime
 - User can learn more in the information page:
   - How to play
   - View company profiles
   - Adjust settings
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



# Needs to be edited
# UML
## Models
### Game
    - int gameId
    - int userId
    - int portfolioId
    - int yearNumber
    - int yearlyScenarioId
    - int currentMarketId
### User
    - int UserId
    - String Username 
    - String password
      - Spring Security LMS
### Company
    - int companyId
    - String Name
    - String companyType
    - int StockPrice
    - String risk
        (Stock)
    - int totalStocksAvailable
    - int currentPrice 
    - int dividend
    - bet Boolean (???? here ????)
      - shift(?)
### Portfolio
    - int portfolioId
    - int userId
    - int companyId
    - int stockCount

### yearlyScenario
    - int yearlyScenarioId
    - int companyTypeId
    - String name
    - boolean bullOrBear

### CurrentMarket
    - int userId
    - int marketId
    - List<Company> companies
    - boolean isBull(bear) 
    - String marketType

### BearMarket
    - int bullMarketId
    - int rollResult
    - int modifier
    - int companyId

### BullMarket
    - int bearMarketId
    - int rollResult
    - int modifier
    - int companyId

## Repository
### bullMarketRepository
    BullMarket findById (companyId)

### bearMarketRepository
    BearMarket findById (companyId)

### CompanyRepository
    List<Company> findAll()
    Company findById (int companyId)
    int readCompanyPriceById(int companyId)
    updateCurrentStockPrice (companyId)

### CompanyTypeRepository   
    String findCompanyTypeById (companyTypeId)

### GameRepository
[comment]: <> (Table needs UserId, PortfolioId, Market, MarketType, yearNumber)
    Game findGameByUserID (UserId)
    Boolean addGameState (Game game)

### UserRepository
    User FindByUserName (String Username)
    boolean addUser (String username)
    boolean deleteUser (userId)

### LeaderboardRepository
    List<User> findAll()
    addHighScore(int UserId, int Score)

### PortfolioRepository
    Portfolio findPortfolioByUserId(userId)
    boolean updatePortfolio(Portfolio)
    boolean addPortfolio(Portfolio)
    boolean deletePortfolio(Portfolio)

### MarketRepository   
    List<Company> findAll()
    Company findById (int companyId)
    int findCompanyPriceById(int companyId)
    boolean addMarket (Market market)
    boolean updateCurrentStockPrice (int companyId)
    boolean setBankrupt(boolean) - handle checking removal of a comapny in the market?
    boolean deleteMarket (userId) - remove company for bankruptcy
    boolean addCompany (int companyId) - adds if less than 10 companies in market

### yearlyScenario
    YearlyScenario findById(yearlyScenarioID)

## Service

### bullMarketService
    BullMarket findById (companyId)

### bearMarketService
    BearMarket findById (companyId)

### CompanyService
    List<Company> findAll()
    Company findById (int companyId)
    int readCompanyPriceById(int companyId)
    updateCurrentStockPrice (companyId)

    updateStockPri

### CompanyTypeService
    String findCompanyTypeById (companyTypeId)

### GameService
[comment]: <> (Table needs UserId, PortfolioId, Market, MarketType, yearNumber)
    Game findGameByUserID (int UserId)
    Boolean addGameState (Game game)
    Boolean deleteGameState (int userId)
[comment]: <> (Everything above needs to be filled ei String username, Market market, int yearnumber, YearlyScenario yearlyScenario)
        - Boolean Verify (Game game)

### UserService
    User FindByUserName (String Username)
    boolean addUser (String username, HashedPassword)
    boolean deleteUser (int userId)

### LeaderboardService
    List<User> findAll()
    addHighScore(int UserId, int Score)

### PortfolioService
    Portfolio findPortfolioByUserId(userId)
    boolean addPortfolio(Portfolio portfolio)
    boolean deletePortfolio(int userId)

### MarketService
    List<Company> findAll()
    Company findById (int companyId)
    int findCompanyPriceById(int companyId)
    boolean addMarket (Market market)
    boolean updateCurrentStockPrice (int companyId)
    boolean setBankrupt(boolean) - handle checking removal of a comapny in the market?
    boolean deleteMarket (userId) - remove company for bankruptcy
    boolean addCompany (int companyId) - adds if less than 10 companies in market

## Controller
### bullMarketController
    @GETMAPPING
    BullMarket findById(comapnyId)

### bearMarketController
    @GETMAPPING
    BearMarket findById(companyId)

### CompanyController
    @GETMAPPING
    List<Company> findAll()
    @GETMAPPING
    Company findById (int companyId)
    @GETMAPPING
    int readCompanyPriceById (int companyId)
    @GETMAPPING
    int findStockAvailable (int companyId)
    @PUTMAPPING
    updateStockAvailableByCompany (int companyId)

### CompanyTypeController
    @GETMAPPING
    String findCompanyTypeById (companyTypeId)

### GameController
[comment]: <> (Table needs UserId, PortfolioId, Market, MarketType, yearNumber)
    @GETMAPPING
    Game findGameByUserID (UserId)
    @POSTMAPPING
    Boolean addGameState (Game game)

### UserController
    @GETMAPPING
    User FindByUserName (String Username)
    @POSTMAPPING
    boolean addUser (String username, HashedPassword)
    @DELETEMAPPING
    boolean deleteUser (userId)

### LeaderboardController
    @GETMAPPING
    List<User> findAll()
    @POSTMAPPING
    addHighScore(int UserId, int Score)

### PortfolioController
    @GETMAPPING
    Portfolio findPortfolioByUserId(userId)
    @POSTMAPPING
    boolean addPortfolio(Portfolio)

### MarketController
    @GETMAPPING
    List<Company> findAll()
    @GETMAPPING
    Company findById (int companyId)
    GETMAPPING
    int readCompanyPriceById(int companyId) -IDK maybe Stockprice
    @PUTMAPPING
    boolean updateCurrentStockPrice (companyId)
    @PUTMAPPING
    boolean setBankrupt(boolean)

### Play
 - createPortfolio
   - Input Companies, stock #/ company
   - Cancel() - Link to playMenu
   - Output Portfolio Object

 - playMenu
   - View ("Input" Game.Portfolio.companies) "Output" Display Portfolio
   - View ("Input" Game.Market.companies) "Output" Display Market

 - View
   - Return Table of Companies

### settings
 - Screen Res
 - audio toggle

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
     - Audit log
