// db.auth($MONGO_INITDB_ROOT_USERNAME, $MONGO_INITDB_ROOT_PASSWORD)
// db = db.getSiblingDB($MONGO_INITDB_DATABASE)
db.createUser(
    {
        user: "sskuratov",
        pwd: "Powell2100!",
        roles: [
            {
                role: "readWrite",
                db: "sodium-consumption-calc"
            }
        ]
    }
)