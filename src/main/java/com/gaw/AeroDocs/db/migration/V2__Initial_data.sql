INSERT INTO users (username, email) VALUES ('blightjl', 'blightjl@github.gh') ON CONFLICT (username) DO NOTHING;
INSERT INTO users (username, email) VALUES ('xtremedamage', 'xtremedamage@lol.rg') ON CONFLICT (username) DO NOTHING;
INSERT INTO users (username, email) VALUES ('1337 leet code god', '1337goated@lc.go') ON CONFLICT (username) DO NOTHING;
INSERT INTO users (username, email) VALUES ('incoming faang swe', 'incomingfaangswe@faang.swe') ON CONFLICT (username) DO NOTHING;

INSERT INTO aircraft_models (manufacturer, model, variant) VALUES ('Boeing', '777X', '8&9') ON CONFLICT (manufacturer, model, variant) DO NOTHING;
INSERT INTO aircraft_models (manufacturer, model, variant) VALUES ('Airbus', 'a350', '1000') ON CONFLICT (manufacturer, model, variant) DO NOTHING;