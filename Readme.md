# Scala Play Acronym Webservice

This currently utilises the Acromine webservice to provide a useful easy to use REST api for acronym definitions

````
http://<host>/v1/acronym/<acronym>?maxResults=100
http://<host>/v1/acronyms?a=<acronym1>&a=<acronym2>&maxResults=100
````
For example:

`http://localhost:9000/v1/acronyms?a=HMM&a=MOD`

Returns:
````json
[
  {
  acronym: "HMM",
  longForms: [
    {
      name: "heavy meromyosin"
    },
    {
      name: "home management of malaria"
    },
    {
      name: "6a-hydroxymaackiain 3-O-methyltransferase"
    }
  ]
  },
  {
  acronym: "MOD",
  longForms: [
    {
      name: "multiple organ dysfunction"
    },
    {
      name: "Ministry of Defence"
    },
    {
      name: "Congenital multiple ocular defects"
    }
  ]
  }
]
````